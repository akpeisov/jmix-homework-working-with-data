package com.sample.carmarket.screen.manufacturer;

import com.sample.carmarket.app.CarBean;
import com.sample.carmarket.entity.EngineType;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Manufacturer.browse")
@UiDescriptor("manufacturer-browse.xml")
@LookupComponent("table")
public class ManufacturerBrowse extends MasterDetailScreen<Manufacturer> {
    @Autowired
    private Notifications notifications;
    @Autowired
    private CarBean carBean;

    @Subscribe("calcCars")
    public void onCalcCarsClick(Button.ClickEvent event) {
        String msg = String.format("Electric cars: %d, gasoline cars: %d",
                        carBean.getCarsCount(EngineType.GASOLINE.getId()),
                        carBean.getCarsCount(EngineType.ELECTRIC.getId()));
        notifications.create().withCaption(msg).show();
    }
}