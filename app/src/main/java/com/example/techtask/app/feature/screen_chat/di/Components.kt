import com.example.techtask.app.feature.screen_chat.di.RepositoryModule
import com.example.techtask.app.feature.screen_chat.di.ViewModelModule
import com.example.techtask.app.feature.screen_chat.presentation.ChatActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(activity: ChatActivity)
}