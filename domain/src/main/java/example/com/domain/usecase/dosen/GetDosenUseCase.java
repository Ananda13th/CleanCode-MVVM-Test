package example.com.domain.usecase.dosen;

import example.com.domain.model.Dosen;
import example.com.domain.repository.DosenRepository;
import example.com.domain.usecase.SingleUseCaseWithParam;
import io.reactivex.Single;

public class GetDosenUseCase implements SingleUseCaseWithParam<String, Dosen> {
    private DosenRepository dosenRepository;

    public GetDosenUseCase(DosenRepository dosenRepository) {
        this.dosenRepository = dosenRepository;
    }

    @Override
    public Single<Dosen> execute(String idDosen) {
        return dosenRepository.doGetDosen(idDosen);
    }
}
