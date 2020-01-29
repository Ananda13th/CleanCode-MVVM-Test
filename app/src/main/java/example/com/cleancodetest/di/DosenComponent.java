package example.com.cleancodetest.di;

import javax.inject.Singleton;

import dagger.Component;
import example.com.cleancodetest.viewmodel.DosenRespViewModel;

@Singleton
@Component(modules = DosenModule.class)
public interface DosenComponent {
    void inject(DosenRespViewModel dosenRespViewModel);
}
