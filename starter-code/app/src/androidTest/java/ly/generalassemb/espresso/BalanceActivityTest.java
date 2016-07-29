package ly.generalassemb.espresso;

/**
 * Created by erikrudie on 7/29/16.
 */

import android.app.Activity;
import android.app.Application;
import android.support.design.widget.FloatingActionButton;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.test.mock.MockContext;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith (AndroidJUnit4.class)
public class BalanceActivityTest  {
    @Rule
    public ActivityTestRule<BalanceActivity> activityTestRule = new ActivityTestRule<BalanceActivity>(BalanceActivity.class);


//    @Test
//    public void depositValidAmount_works() {
////        FloatingActionButton mNewTransactionButton = (FloatingActionButton) MockContext.findViewById(R.id.newTransactionButton);
//
//        onView(withId(R.id.newTransactionButton)).perform(click());
//
//
//    }

    @Test
    public void newTransaction_changesActivity() {
        onView(withId(R.id.newTransactionButton)).perform(click());
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
    }

    @Test
    public void displayedBalance_updates() {
        // jump to new activity
        onView(withId(R.id.newTransactionButton)).perform(click());

        // type into description edittext
        onView(withId(R.id.descriptionEditText)).perform(typeText("desc"));

        // type into amount edittext
        onView(withId(R.id.amountEditText)).perform(typeText("5"));

        // click deposit
        onView(withId(R.id.depositButton)).perform(click());

        // check amount
        onView(withId(R.id.balanceTextView)).check(matches(withText("$5.00")));
    }

    @Test
    public void displayedBalance_updatesNegative() {
        // jump to new activity
        onView(withId(R.id.newTransactionButton)).perform(click());

        // type into description edittext
        onView(withId(R.id.descriptionEditText)).perform(typeText("desc"));

        // type into amount edittext
        onView(withId(R.id.amountEditText)).perform(typeText("5"));

        // click deposit
        onView(withId(R.id.withdrawButton)).perform(click());

        // check amount
        onView(withId(R.id.balanceTextView)).check(matches(withText("-$5.00")));
    }

    @Test
    public void firstDescription_updates() {
        // jump to new activity
        onView(withId(R.id.newTransactionButton)).perform(click());

        // type into description edittext
        onView(withId(R.id.descriptionEditText)).perform(typeText("desc"));

        // type into amount edittext
        onView(withId(R.id.amountEditText)).perform(typeText("5"));

        // click deposit
        onView(withId(R.id.withdrawButton)).perform(click());

        // check amount
        onView(withId(R.id.balance_item_description)).check(matches(withText("desc")));
    }

    @Test
    public void multipleDescriptions_update() {
        // jump to new activity
        onView(withId(R.id.newTransactionButton)).perform(click());

        // type into description edittext
        onView(withId(R.id.descriptionEditText)).perform(typeText("first"));

        // type into amount edittext
        onView(withId(R.id.amountEditText)).perform(typeText("5"));

        // click deposit
        onView(withId(R.id.withdrawButton)).perform(click());

        // jump to new activity
        onView(withId(R.id.newTransactionButton)).perform(click());

        // type into description edittext
        onView(withId(R.id.descriptionEditText)).perform(typeText("second"));

        // type into amount edittext
        onView(withId(R.id.amountEditText)).perform(typeText("5"));

        // click deposit
        onView(withId(R.id.withdrawButton)).perform(click());

        // jump to new activity
        onView(withId(R.id.newTransactionButton)).perform(click());

        // type into description edittext
        onView(withId(R.id.descriptionEditText)).perform(typeText("third"));

        // type into amount edittext
        onView(withId(R.id.amountEditText)).perform(typeText("5"));

        // click deposit
        onView(withId(R.id.withdrawButton)).perform(click());

        // check descriptions
//        onData(withId(R.id.transactionsListView)).inAdapterView(withId(R.id.transactionsListView))
//                .atPosition(0).onChildView(withId(R.id.balance_item_description))
//                .check(matches(withText("first")));
        onData(withId(R.id.balance_item_description)).inAdapterView(withId(R.id.transactionsListView))
                .atPosition(0)
                .check(matches(withText("first")));
    }


}
