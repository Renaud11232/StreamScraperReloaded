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

/**
 * Class that represents a {@link Stream} with its metadata
 */
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

    /**
     * Constructs a new {@link Stream} object
     */
    public Stream() {
        clear();
    }

    /**
     * Gets a {@link String} representatio of this {@link Stream}
     *
     * @return the {@link String} representation
     */
    @Override
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

    /**
     * Clears this {@link Stream}<br/>
     * Resets all metadata to <code>null</code> or <code>-1</code>
     */
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

    /**
     * Merges 2 {@link Stream} metadata into one
     *
     * @param another the other {@link Stream} to merge with
     */
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

    /**
     * Gets the title of this {@link Stream}
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this {@link Stream}
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Gets the description of this {@link Stream}
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this {@link Stream}
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the {@link URI} of this {@link Stream}
     *
     * @return the {@link URI}
     */
    public URI getUri() {
        return uri;
    }

    /**
     * Sets the {@link URI} of this {@link Stream}
     *
     * @param uri the new {@link URI}
     */
    public void setUri(URI uri) {
        this.uri = uri;
    }


    /**
     * Gets the current listener count of this {@link Stream}
     *
     * @return the current listener count
     */
    public int getCurrentListenerCount() {
        return currentListenerCount;
    }

    /**
     * Sets the current listener count of this {@link Stream}
     *
     * @param currentListenerCount the new current listener count
     */
    public void setCurrentListenerCount(int currentListenerCount) {
        this.currentListenerCount = currentListenerCount;
    }


    /**
     * Gets the maximum listener count of this {@link Stream}
     *
     * @return the maximum listener count
     */
    public int getMaxListenerCount() {
        return maxListenerCount;
    }

    /**
     * Sets the maximum listener count of this {@link Stream}
     *
     * @param maxListenerCount the new maximum listener count
     */
    public void setMaxListenerCount(int maxListenerCount) {
        this.maxListenerCount = maxListenerCount;
    }


    /**
     * Gets the peak listener count of this {@link Stream}
     *
     * @return the peak listener count
     */
    public int getPeakListenerCount() {
        return peakListenerCount;
    }

    /**
     * Sets the peak listener count of this {@link Stream}
     *
     * @param peakListenerCount the new peak listener count
     */
    public void setPeakListenerCount(int peakListenerCount) {
        this.peakListenerCount = peakListenerCount;
    }


    /**
     * Gets the bit rate of this {@link Stream}
     *
     * @return the bit rate
     */
    public String getBitRate() {
        return bitRate;
    }

    /**
     * Sets the bit rate of this {@link Stream}
     *
     * @param bitRate the new bit rate
     */
    public void setBitRate(String bitRate) {
        this.bitRate = bitRate;
    }


    /**
     * Gets the current sont of this {@link Stream}
     *
     * @return the current song
     */
    public String getCurrentSong() {
        return currentSong;
    }

    /**
     * Sets the current song of this {@link Stream}
     *
     * @param currentSong the new current song
     */
    public void setCurrentSong(String currentSong) {
        this.currentSong = currentSong;
    }


    /**
     * Gets the content type of this {@link Stream}
     *
     * @return the content type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the content type of this {@link Stream}
     *
     * @param contentType the new content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Gets the title of this {@link Stream}
     *
     * @return the title
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of this {@link Stream}
     *
     * @param genre the new genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
