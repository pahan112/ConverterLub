package papka.pahan.converterlub.activity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import papka.pahan.converterlub.R;
import papka.pahan.converterlub.db.ModelDataBaseBank;

/**
 * Created by admin on 03.05.2017.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String BANK_MAP = "bank_map";

    @BindView(R.id.tv_city_setting_map)
    TextView mMapCityTextView;
    @BindView(R.id.tv_title_setting_map)
    TextView mMapTitleTextView;

    private GoogleMap mGoogleMap;
    private ModelDataBaseBank mModelBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        mModelBank = (ModelDataBaseBank) getIntent().getSerializableExtra(BANK_MAP);

        mMapCityTextView.setText(mModelBank.getCityIdDb());
        mMapTitleTextView.setText(mModelBank.getTitleDb());
        SupportMapFragment supportMapFragment = new SupportMapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.map, supportMapFragment, SupportMapFragment.class.getSimpleName()).commit();
        supportMapFragment.getMapAsync(this);
    }

    @OnClick(R.id.iv_back_map)
    void clickBack() {
        onBackPressed();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        mGoogleMap.setMapType(1);
        List<Address> addressList = new ArrayList();
        try {
            addressList = new Geocoder(this).getFromLocationName(mModelBank.getAddressDb(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addressList.isEmpty()) {
            Toast.makeText(this, "Банк не найден", Toast.LENGTH_LONG).show();
            return;
        }
        Address address = (Address) addressList.get(0);
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        mGoogleMap.addMarker(new MarkerOptions().position(latLng).title("Bank Marker"));
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(latLng).zoom(18.0f).bearing(0.0f).tilt(0.0f).build()));
        mGoogleMap.setPadding(0, 320, 0, 0);
    }

}

