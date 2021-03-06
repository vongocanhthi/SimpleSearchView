# Simple Search View [![](https://jitpack.io/v/vongocanhthi/SimpleSearchView.svg)](https://jitpack.io/#vongocanhthi/SimpleSearchView)

<img src="https://i.imgur.com/sPDiXWM.gif" width="260" height="426" />

# Installation
**Step 1**. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

        allprojects {
        repositories {
        	...
        	maven { url 'https://jitpack.io' }
            }
        }
  
**Step 2**. Add the dependency

[![](https://jitpack.io/v/vongocanhthi/SimpleSearchView.svg)](https://jitpack.io/#vongocanhthi/SimpleSearchView)

        dependencies {
            implementation 'com.github.vongocanhthi:SimpleSearchView:$version'
        }

# Usage
## XML:

	<com.vnat.library.view.SimpleSearchView
	    android:id="@+id/simpleSearchView"
	    android:layout_width="match_parent"
	    android:layout_height="wrap_content"/>

## Attribute

	<com.vnat.library.view.SimpleSearchView
            android:id="@+id/simpleSearchView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:ssv_cursorVisible="true"
            app:ssv_hint="Search"
            app:ssv_text=""
            app:ssv_textColor="@color/black"
            app:ssv_textColorHint="@color/hint"
            app:ssv_textSize="18sp" />
	    
## Submit Suggestion List:
**First**, you should submit a list of suggestions in order to be able to query your search.

        simpleSearchView.submitSuggestionList(mSuggestionList);
       
## Event listeners:
### Listen for query events:

        simpleSearchView.setOnQueryTextChangeListener(new OnQueryTextChangeListener() {
            @Override
            public void onQueryTextChange(String queryText) {
                // get suggestions based on "queryText"
            }

            @Override
            public void onQueryTextSubmit(String queryText) {
                // event when pressing search
            }
        });
        
### Listen for suggested click events:
        
        simpleSearchView.setOnSuggestionListener(new OnSuggestionListener() {
            @Override
            public void onSuggestionClick(String suggestionText, int position) {
		// ...
            }

            @Override
            public void onSuggestionLongClick(String suggestionText, int position) {
		// ...
            }
        });
        
### Listen to action left events:

        simpleSearchView.setOnActionLeftIconListener(new OnActionLeftIconListener() {
            @Override
            public void onClick() {
		// ...
            }

            @Override
            public void onLongClick() {
		// ...
            }
        });

## Custom icons:

        // change icon action
        simpleSearchView.setLeftSuggestionIconChange(R.drawable.ic_search_black);
        simpleSearchView.setRightSuggestionIconChange(R.drawable.ic_arrow_up_left_black);

        // change icon suggestion
        simpleSearchView.setLeftActionIconChange(R.drawable.ic_search_black);
        simpleSearchView.setRightActionIconChange(R.drawable.ic_clear_black);
      
     
# License
    Copyright [2021] [Võ Ngọc Anh Thi]

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
