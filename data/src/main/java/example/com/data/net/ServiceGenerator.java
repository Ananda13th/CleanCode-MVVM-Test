package example.com.data.net;

import example.com.data.BuildConfig;

public class ServiceGenerator {
    private static DosenService dosenService;

    public  static  DosenService getDosenService() {
        if(dosenService == null) {
            dosenService = RetrofitHelper.getRetrofit(BuildConfig.BASE_URL_DOSEN).create(DosenService.class);
        }
        return dosenService;
    }
}
