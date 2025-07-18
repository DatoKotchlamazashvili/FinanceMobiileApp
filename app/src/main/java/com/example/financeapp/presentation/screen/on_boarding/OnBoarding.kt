package com.example.financeapp.presentation.screen.on_boarding

import com.example.financeapp.R
import com.example.financeapp.ui.design_system.button.ButtonType
import com.example.financeapp.ui.design_system.text.TextType

data class OnBoarding(
    val imageRes: Int,
    val mainText: String,
    val subtext: String,
    val mainButtonText: String,
    val subButtonText: String,
    val subButtonType: OnBoardingSubButtonType
)

enum class OnBoardingSubButtonType{
    UNDERLINE_TEXT,SUB_BUTTON
}



fun getOnBoardingData(): List<OnBoarding> =
    listOf(
        OnBoarding(
            imageRes = R.drawable.ic_send_money,
            mainText = "Send Money",
            subtext = "Send money easily and with one click everything will be sent every time you make a transaction",
            mainButtonText = "Next Step",
            subButtonText = "Skip this Step",
            subButtonType = OnBoardingSubButtonType.UNDERLINE_TEXT
        ),
        OnBoarding(
            imageRes = R.drawable.ic_receive_money,
            mainText = "Request Money",
            subtext = "You can request money to friends or family to send as much money as you want",
            mainButtonText = "Next Step",
            subButtonText = "Skip this Step",
            subButtonType = OnBoardingSubButtonType.UNDERLINE_TEXT

        ),
        OnBoarding(
            imageRes = R.drawable.ic_hand,
            mainText = "Easy To Use",
            subtext = "Very easy to use and easy to understand for those of you who are beginners",
            mainButtonText = "Next Step",
            subButtonText = "Skip this Step",
            subButtonType = OnBoardingSubButtonType.SUB_BUTTON

        ),

        )