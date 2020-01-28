package example.com.cleancodetest.model.mapper;

import example.com.cleancodetest.model.BaseResponseModel;
import example.com.domain.model.BaseResponse;

public class BaseResponseMapper {

    public BaseResponseModel baseResponseToView(BaseResponse baseResponse) {
        BaseResponseModel  baseResponseModel = new BaseResponseModel();
        baseResponseModel.setErrorMesssage(baseResponse.getErrorMesssage());
        baseResponseModel.setErrorCode(baseResponse.getErrorCode());
        baseResponseModel.setEpoch(baseResponse.getEpoch());
        return baseResponseModel;
    }
}
