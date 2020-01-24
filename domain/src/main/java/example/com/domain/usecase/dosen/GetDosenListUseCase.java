package example.com.domain.usecase.dosen;

import example.com.domain.model.DosenResp;
import example.com.domain.repository.DosenRepository;
import example.com.domain.usecase.SingleUseCase;
import io.reactivex.Single;

public class GetDosenListUseCase implements SingleUseCase<DosenResp> {
    private DosenRepository dosenRepository;

    public GetDosenListUseCase(DosenRepository dosenRepository) {
        this.dosenRepository = dosenRepository;
    }

    @Override
    public Single<DosenResp> execute() {
        return dosenRepository.doGetListDosen();
    }
}
