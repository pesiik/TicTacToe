package mc.pesiik.tictactoe.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mc.pesiik.tictactoe.data.GridRepositoryImpl
import mc.pesiik.tictactoe.repository.GridRepository

@Module
@InstallIn(ViewModelComponent::class)
internal interface DomainModule {

    @Binds
    fun bindGridRepository(
        impl: GridRepositoryImpl
    ): GridRepository
}