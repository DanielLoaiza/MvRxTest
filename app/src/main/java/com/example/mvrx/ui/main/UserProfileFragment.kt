package com.example.mvrx.ui.main

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import com.example.mvrx.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_user_profile.*

data class UserProfileState(val userId: String) : MvRxState {
    constructor(args: UserProfileArgs): this(args.userId)
}

data class UserProfileStateAsync(val user: Async<User> = Uninitialized): MvRxState

@Parcelize
class UserProfileArgs(val userId: String): Parcelable


class UserProfileViewModel(userProfileState: UserProfileState): MvRxViewModel<UserProfileState>(userProfileState) {

//    companion object: MvRxViewModelFactory<UserProfileViewModel, UserProfileState> {
//        override fun initialState(viewModelContext: ViewModelContext): UserProfileState {
//            val userId = (viewModelContext as FragmentViewModelContext).fragment<UserProfileFragment>().getUserId()
//
//            return UserProfileState(userId)
//        }
//    }
}

class UserProfileViewModelAsync(private val userProfileStateAsync: UserProfileStateAsync, private  val userRepository: UserRepository, private val userId: String):
    MvRxViewModel<UserProfileStateAsync>(userProfileStateAsync) {

    fun fetchUser() {
        userRepository.getUser(userId).execute { copy(user = it)}
    }
    companion object: MvRxViewModelFactory<UserProfileViewModelAsync, UserProfileStateAsync> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: UserProfileStateAsync
        ): UserProfileViewModelAsync? {
            val userId = viewModelContext.args<UserProfileArgs>().userId
            val userRepository = viewModelContext.app<MvRxApplication>().component.userRepository()
            return UserProfileViewModelAsync(state, userRepository, userId)

        }
    }
}

class UserProfileFragment : BaseMvRxFragment() {


    private val viewModel: UserProfileViewModelAsync by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    fun getUserId() = arguments?.getString(ARG_USER_ID)
        ?: throw IllegalArgumentException("you must provide an user id")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        message.setOnClickListener {
            viewModel.fetchUser()
        }
    }

    override fun invalidate() = withState(viewModel) {
        message.text = it.user.toString()
    }

    companion object {
        private const val ARG_USER_ID = "user_id"

        fun newInstance(userId: String): UserProfileFragment {
            val fragment = UserProfileFragment()
            val args = Bundle()
            //args.putString(ARG_USER_ID, userId)
            args.putParcelable(MvRx.KEY_ARG, UserProfileArgs(userId))
            fragment.arguments = args
            return fragment
        }
    }
}
