package example.com.cleancodetest.di;

import dagger.Module;
import dagger.Provides;
import example.com.cleancodetest.model.mapper.BaseResponseMapper;
import example.com.cleancodetest.model.mapper.DosenMapper;
import example.com.data.net.DosenService;
import example.com.data.net.ServiceGenerator;
import example.com.data.repository.BaseResponseRepositoryImpl;
import example.com.data.repository.DosenRepositoryImpl;
import example.com.domain.repository.BaseResponseRepository;
import example.com.domain.repository.DosenRepository;
import example.com.domain.usecase.dosen.AddDosenUseCase;
import example.com.domain.usecase.dosen.DeleteDosenUseCase;
import example.com.domain.usecase.dosen.GetDosenListUseCase;
import example.com.domain.usecase.dosen.GetDosenUseCase;
import example.com.domain.usecase.dosen.UpdateDosenUseCase;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

@Module
public class DosenModule {

    @Provides
    BaseResponseMapper provideBaseResponseMapper() {return  new BaseResponseMapper(); }

    @Provides
    DosenMapper provideDosenMapper() {
        return new DosenMapper();
    }

    @Provides
    example.com.data.entity.mapper.DosenMapper provideDosenMapperEntity() { return  new example.com.data.entity.mapper.DosenMapper();
    }

    @Provides
    DosenService provideDosenService() {
        return ServiceGenerator.getDosenService();
    }

    @Provides
    Scheduler provideSchedulers() {
        return Schedulers.io();
    }

    @Provides
    DosenRepository provideDosenRepository(example.com.data.entity.mapper.DosenMapper dosenMapper, Scheduler scheduler, DosenService dosenService) {
        return new DosenRepositoryImpl(dosenMapper,scheduler,dosenService);
    }

    @Provides
    BaseResponseRepository provideBaseResponseRepository(example.com.data.entity.mapper.DosenMapper dosenMapper, Scheduler scheduler, DosenService dosenService) {
        return new BaseResponseRepositoryImpl(dosenMapper,scheduler,dosenService);
    }
    @Provides
    GetDosenUseCase provideGetDosenUseCase(DosenRepository dosenRepository) {
        return new GetDosenUseCase(dosenRepository);
    }

    @Provides
    GetDosenListUseCase provideGetDosenListUseCase(DosenRepository dosenRepository) {
        return new GetDosenListUseCase(dosenRepository);
    }

    @Provides
    AddDosenUseCase provideAddDosenUseCase(BaseResponseRepository baseResponseRepository) {
        return new AddDosenUseCase(baseResponseRepository);
    }

    @Provides
    DeleteDosenUseCase provideDeleteDosenUseCase(BaseResponseRepository baseResponseRepository) {
        return new DeleteDosenUseCase(baseResponseRepository);
    }

    @Provides
    UpdateDosenUseCase provideUpdateDosenUseCase(BaseResponseRepository baseResponseRepository) {
        return new UpdateDosenUseCase(baseResponseRepository);
    }
}
