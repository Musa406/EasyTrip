package com.example.whoami.easybook;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomTruckListAdaptor extends ArrayAdapter<VehicleInfoSaveRetrieve> {

    private Activity context;
    private List<VehicleInfoSaveRetrieve> vehicleLists;
    static int tripFare;


    public CustomTruckListAdaptor(Activity context, List<VehicleInfoSaveRetrieve> vehicleLists) {

        super(context, R.layout.custom_layout_for_search_vehicle, vehicleLists);
        this.context = context;
        this.vehicleLists = vehicleLists;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.custom_layout_for_search_vehicle, null, true);

        VehicleInfoSaveRetrieve vehicleList = vehicleLists.get(position);

        TextView vehicleNumberPlate =  listViewItem.findViewById(R.id.vehicleNumberPlateId);
        TextView vehicleCarryingCapacity =  listViewItem.findViewById(R.id.vehicleCarryingCapacityId);
        TextView weightCapacityId = listViewItem.findViewById(R.id.vehicleWeightCapacity);
        TextView vehicleType = listViewItem.findViewById(R.id.vehicleTypeId);
        TextView tripCost = listViewItem.findViewById(R.id.tripCostId);


        vehicleNumberPlate.setText("VN: #"+vehicleList.getNumberPlate());
        vehicleCarryingCapacity.setText("capacity: "+vehicleList.getCarryingCapacity());
        weightCapacityId.setText("Weight Capacity: "+vehicleList.getWeightCapacity());
        vehicleType.setText("Type: "+vehicleList.getVehicleType());

        double tripcost = SearchAvailability.cost;
        //String.format("%.1f%n", tripcost);
        int intCost = (int)tripcost;


        ImageView vehicleImage = listViewItem.findViewById(R.id.truckImageId);

        if(vehicleList.getVehicleType().toString().equals("covered truck")){
            vehicleImage.setImageResource(R.drawable.covered_truck);
            tripFare = (int)(intCost+ (intCost*.20));
            tripCost.setText("Fare: "+tripFare+" Tk.");
        }
        else if(vehicleList.getVehicleType().toString().equals("mini truck")){
            vehicleImage.setImageResource(R.drawable.mini_truck3);
            tripFare = (int)(intCost- (intCost*.50));
            tripCost.setText("Fare: "+tripFare+" Tk.");
        }
        else{
            vehicleImage.setImageResource(R.drawable.opened_truck);
            tripFare  = (int)intCost;
            tripCost.setText("Fare: "+tripFare+" Tk.");
        }

        return listViewItem ;
    }
}