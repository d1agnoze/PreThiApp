package vdac.prethi.app;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import vdac.prethi.app.models.Geo;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Geo> uiState = new MutableLiveData<>();

    public MainViewModel() {
        this.uiState = new MutableLiveData<>(new Geo());
    }

    public MutableLiveData<Geo> getUiState() {
        return uiState;
    }

}
