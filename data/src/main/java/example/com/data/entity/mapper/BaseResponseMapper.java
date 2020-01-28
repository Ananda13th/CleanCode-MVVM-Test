package example.com.data.entity.mapper;

import example.com.data.entity.BaseResponseEntity;
import example.com.domain.model.BaseResponse;

public class BaseResponseMapper {

    public BaseResponse baseResponseToDomain(BaseResponseEntity baseResponseEntity) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setEpoch(baseResponseEntity.getEpoch());
        baseResponse.setErrorCode(baseResponseEntity.getErrorCode());
        baseResponse.setErrorMesssage(baseResponseEntity.getErrorMesssage());
        return baseResponse;
    }
}
