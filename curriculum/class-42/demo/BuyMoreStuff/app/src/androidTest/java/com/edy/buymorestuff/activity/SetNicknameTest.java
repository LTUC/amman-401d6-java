package com.edy.buymorestuff.activity;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.edy.buymorestuff.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SetNicknameTest
{

  @Rule
  public ActivityTestRule<ProductListActivity> mActivityTestRule = new ActivityTestRule<>(ProductListActivity.class);

  @Test
  public void setNicknameTest()
  {
    ViewInteraction appCompatImageView = onView(
      allOf(withId(R.id.userSettingsImageView),
        childAtPosition(
          allOf(withId(R.id.productListConstraintLayout),
            childAtPosition(
              withId(android.R.id.content),
              0)),
          3),
        isDisplayed()));
    appCompatImageView.perform(click());

    ViewInteraction appCompatEditText = onView(
      allOf(withId(R.id.nicknameInputEditText),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          2),
        isDisplayed()));
    appCompatEditText.perform(replaceText("RoboEd"), closeSoftKeyboard());

    ViewInteraction appCompatEditText2 = onView(
      allOf(withId(R.id.nicknameInputEditText), withText("RoboEd"),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          2),
        isDisplayed()));
    appCompatEditText2.perform(pressImeActionButton());

    ViewInteraction materialButton = onView(
      allOf(withId(R.id.userSettingsSaveButton), withText("Save"),
        childAtPosition(
          childAtPosition(
            withId(android.R.id.content),
            0),
          3),
        isDisplayed()));
    materialButton.perform(click());

    pressBack();

    ViewInteraction textView = onView(
      allOf(withId(R.id.productListNicknameTextView), withText("Nickname: RoboEd"),
        withParent(allOf(withId(R.id.productListConstraintLayout),
          withParent(withId(android.R.id.content)))),
        isDisplayed()));
    textView.check(matches(withText("Nickname: RoboEd")));
  }

  private static Matcher<View> childAtPosition(
    final Matcher<View> parentMatcher, final int position)
  {

    return new TypeSafeMatcher<View>()
    {
      @Override
      public void describeTo(Description description)
      {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view)
      {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
          && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
