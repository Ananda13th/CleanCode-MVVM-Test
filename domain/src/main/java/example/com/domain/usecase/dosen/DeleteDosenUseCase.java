package example.com.domain.usecase.dosen;

import example.com.domain.model.BaseResponse;
import example.com.domain.model.DosenResp;
import example.com.domain.repository.BaseResponseRepository;
import example.com.domain.repository.DosenRepository;
import example.com.domain.usecase.SingleUseCaseWithParam;
import io.reactivex.Single;

public class DeleteDosenUseCase implements SingleUseCaseWithParam <String, BaseResponse> {

    private final BaseResponseRepository baseResponseRepository;

    public DeleteDosenUseCase(BaseResponseRepository baseResponseRepository) {
        this.baseResponseRepository = baseResponseRepository;
    }

    @Override
    public Single<BaseResponse> execute(String parameter) {
        return baseResponseRepository.doDeleteDosen(parameter);
    }
}
