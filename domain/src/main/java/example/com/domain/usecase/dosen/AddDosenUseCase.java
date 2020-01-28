package example.com.domain.usecase.dosen;

import example.com.domain.model.BaseResponse;
import example.com.domain.model.Dosen;
import example.com.domain.repository.BaseResponseRepository;
import example.com.domain.usecase.SingleUseCaseWithParam;
import io.reactivex.Single;

public class AddDosenUseCase implements SingleUseCaseWithParam<Dosen, BaseResponse> {
    private final BaseResponseRepository baseResponseRepository;

    public AddDosenUseCase(BaseResponseRepository baseResponseRepository) {
        this.baseResponseRepository = baseResponseRepository;
    }

    @Override
    public Single<BaseResponse> execute(Dosen parameter) {
        return baseResponseRepository.doInsertDosen(parameter);
    }
}
