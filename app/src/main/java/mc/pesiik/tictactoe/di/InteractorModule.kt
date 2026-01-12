package mc.pesiik.tictactoe.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mc.pesiik.tictactoe.data.GridInteractorImpl
import mc.pesiik.tictactoe.interactor.GridInteractor

@Module
@InstallIn(ViewModelComponent::class)
internal interface InteractorModule {

    @Binds
    fun bindGridInteractor(
        impl: GridInteractorImpl
    ): GridInteractor
}