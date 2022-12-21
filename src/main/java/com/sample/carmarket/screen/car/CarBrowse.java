package com.sample.carmarket.screen.car;

import com.sample.carmarket.entity.CarStatus;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;


@UiController("Car.browse")
@UiDescriptor("car-browse.xml")
@LookupComponent("carsTable")
public class CarBrowse extends StandardLookup<Car> {
    private static final Logger log = LoggerFactory.getLogger(CarBrowse.class);

    @Autowired
    private DataManager dataManager;
    @Autowired
    private CollectionContainer<Car> carsDc;
    @Autowired
    private Notifications notifications;
    @Autowired
    private MessageBundle messageBundle;

    @Subscribe("markAsSold")
    public void onMarkAsSold(Action.ActionPerformedEvent event) {
        Car car = carsDc.getItem();
        if (car.getStatus() == null) {
            notifications.create()
                    .withCaption(messageBundle.getMessage("noStatus"))
                    .show();
            return;
        }
        if (car.getStatus().equals(CarStatus.IN_STOCK)) {
            car.setStatus(CarStatus.SOLD);
            car.setDateOfSale(LocalDate.now());
            dataManager.save(car);

            notifications.create()
                    .withCaption(messageBundle.getMessage("done"))
                    .show();
        } else {
            notifications.create()
                    .withCaption(messageBundle.getMessage("alreadySold"))
                    .show();
        }

                /*
        loan.setStatus(LoanStatus.APPROVED);
        dataManager.save(loan);
        loansDl.load();

                 */


    }
}