GiphySearch
===========

Allows you to easily search Giphy and get the results back for rendering as you wish.

<img src="https://raw.githubusercontent.com/bakkenbaeck/AndroidGiphySearch/master/demo.gif" width="350">

Gradle
------
```groovy
dependencies {
    compile 'com.bakkenbaeck:giphysearch:1.0'
}
```

Usage
-----

Implement GiphyListener.
```java
    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements GiphyListener {
        @Override
        public void onGifsLoaded(List<GiphyData> gifs) {
            // This is called when the gifs have loaded
        }
    }

```

Initialise GiphySearch by passing it your GiphyListener implementation.
```java
    private void initGiphySearch() {
        final RecyclerAdapter adapter = new RecyclerAdapter(this);
        final GiphySearch giphySearch = new GiphySearch(adapter);
        giphySearch.search("make it rain!");
    }
```

The sample contains a full implementation using RecyclerView.

License
-------

    Copyright 2016 Bakken & Bæck

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
