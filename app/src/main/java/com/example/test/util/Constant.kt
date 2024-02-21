package com.example.test.util

object Constant {
    const val BASE_URL = "https://3nt-demo-backend.azurewebsites.net/Access/"
    const val USER_ID_INFO = "Το πεδίο UserId δέχεται μόνο αριθμούς και γράμματα. Πρέπει να ξεκινάει με 2 κεφαλαία και να ακολουθούν 4 αριθμοί."
    const val PASSWORD_INFO = "Τουλάχιστον 8 χαρακτήρες (2 κεφαλαία, 3 πεζά, 1 ειδικός χαρακτήρας, 2 νούμερα)"
    const val USERID_REGEX_PATTERN = """^[A-Z]{2}\d{4}${'$'}"""
    const val PASSWORD_REGEX_PATTERN = """^(?=.*[A-Z].*[A-Z])(?=.*[!@#${'$'}%^&*()])(?=.*\d.*\d)(?=.*[a-z].*[a-z].*[a-z]).{8,}${'$'}"""
    const val SUBMIT_BTN_TEXT = "Σύνδεση"
    const val HIDE_BTN_TEXT = "Κρύψιμο"
    const val SHOW_BTN_TEXT = "Προβολή"
    const val PASSWORD_FIELD_HEADER = "Κωδικός"
    const val USERID_FIELD_HEADER = "UserID"
    const val LOGIN_SCREEN_HEADER_TEXT = "Σύνδεση"
    const val BOOKS_SCREEN_HEADER_TEXT = "Περιοδικά"
    const val ALERT_DIALOG_TITLE = "Λανθασμένα στοιχεία"
    const val ALERT_DIALOG_TEXT = "Εχετε υποβάλλει λάθος στοιχέια"
    const val ALERT_DIALOG_BTN_TEXT = "Επιστροφή"
}