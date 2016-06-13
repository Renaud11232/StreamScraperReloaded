/*
 * Copyright (c) 2016 R. Gaspard
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package be.renaud11232.streamscraperreloaded;


import java.io.Serializable;
import java.net.URI;

public class Stream implements Serializable {

    private String title;
    private String description;
    private URI uri;
    private int currentListenerCount;
    private int maxListenerCount;
    private int peakListenerCount;
    private String bitRate;
    private String currentSong;
    private String contentType;
    private String genre;

    public Stream() {
        clear();
    }

    public String toString() {
        return ("Stream(title=" + title +
                ", desc=" + description +
                ", uri=" + uri +
                ", lc=" + currentListenerCount +
                ", mlc=" + maxListenerCount +
                ", plc=" + peakListenerCount +
                ", br=" + bitRate +
                ", song=" + currentSong +
                ", mime=" + contentType +
                ", genre=" + genre + ")");
    }

    public void clear() {
        title = null;
        description = null;
        uri = null;
        currentListenerCount = -1;
        maxListenerCount = -1;
        peakListenerCount = -1;
        bitRate = null;
        currentSong = null;
        contentType = null;
        genre = null;
    }

    public void merge(Stream another) {
        if (title == null) {
            title = another.getTitle();
        }
        if (description == null) {
            description = another.getDescription();
        }
        if (uri == null) {
            uri = another.getUri();
        }
        if (currentListenerCount < 0) {
            currentListenerCount = another.getCurrentListenerCount();
        }
        if (maxListenerCount < 0) {
            maxListenerCount = another.getMaxListenerCount();
        }
        if (peakListenerCount < 0) {
            peakListenerCount = another.getPeakListenerCount();
        }
        if (bitRate == null) {
            bitRate = another.getBitRate();
        }
        if (currentSong == null) {
            currentSong = another.getCurrentSong();
        }
        if (contentType == null) {
            contentType = another.getContentType();
        }
        if (genre == null) {
            genre = another.getGenre();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public int getCurrentListenerCount() {
        return currentListenerCount;
    }

    public void setCurrentListenerCount(int currentListenerCount) {
        this.currentListenerCount = currentListenerCount;
    }

    public int getMaxListenerCount() {
        return maxListenerCount;
    }

    public void setMaxListenerCount(int maxListenerCount) {
        this.maxListenerCount = maxListenerCount;
    }

    public int getPeakListenerCount() {
        return peakListenerCount;
    }

    public void setPeakListenerCount(int peakListenerCount) {
        this.peakListenerCount = peakListenerCount;
    }

    public String getBitRate() {
        return bitRate;
    }

    public void setBitRate(String bitRate) {
        this.bitRate = bitRate;
    }

    public String getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(String currentSong) {
        this.currentSong = currentSong;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
