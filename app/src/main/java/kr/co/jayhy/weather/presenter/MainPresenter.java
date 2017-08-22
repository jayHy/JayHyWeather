package kr.co.jayhy.weather.presenter;

import kr.co.jayhy.weather.contract.MainContract;

/**
 * Created by jayhy on 2017. 8. 16..
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view = null;

    @Override
    public void setView(MainContract.View view) {
        this.view = view;

    }

}
