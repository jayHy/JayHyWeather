package kr.co.jayhy.weather.contract;

/**
 * Created by jayhy on 2017. 8. 16..
 */

public interface MainContract {

    interface View {
        // View method
    }

    interface Presenter {
        // Presenter method
        void setView(View view);
    }

}
