/**
 * Copyright (c) 2017 Dennis Lang (LanDen Labs) landenlabs@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * @author Dennis Lang  (1/10/2017)
 * @see http://landenlabs.com
 *
 */

package com.landenlabs.allperfimages.tests.string;

import java.util.Arrays;

/**
 * @author Dennis Lang (LanDen Labs)
 * @see <a href="http://landenlabs.com/android/index-m.html"> author's web-site </a>
 */
public class StringAppender2 {
    private String[] mArray = null;
    private int mSize = 0;

    private char[] m_allParts;

    public StringAppender2() {
        ensureCapacityInternal(16);
    }

    public StringAppender2(int initialCapacity) {
        ensureCapacityInternal(initialCapacity);
    }

    /***
     * Start building a string.
     * @param str
     * @return this
     */
    public StringAppender2 start(String str) {
        clear();
        return append(str);
    }

    /***
     * Append to working string
     * @param str
     * @return this
     */
    public StringAppender2 append(String str) {
        add(str);
        return this;
    }

    public void clear() {
        mSize = 0;
    }

    public void add(String str) {
        ensureCapacityInternal(mSize + 1);
        mArray[mSize++] = str;
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (mArray == null) {
            mArray = new String[minCapacity];
            return;
        }

        if (minCapacity <= mArray.length) {
            return;
        }

        // overflow-conscious code
        int oldCapacity = mArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;

        // minCapacity is usually close to size, so this is a win:
        mArray = Arrays.copyOf(mArray, newCapacity);
    }

    /***
     * @return length of all parts.
     */
    public int length() {
        int length = 0;
        if (mArray != null) {
            for (int idx =0; idx < mSize; idx++) {
                String str = mArray[idx];
                length += str.length();
            }
        }
        return length;
    }

    /***
     * @return combination of parts.
     */
    public String toString() {
        int totalLength = length();
        if (totalLength == 0)
            return "";

        if (m_allParts == null || m_allParts.length < totalLength)
            m_allParts = new char[totalLength];

        int pos = 0;
        for (int idx = 0; idx < mSize; idx++) {
            String str = mArray[idx];
            int len = str.length();
            str.getChars(0, len, m_allParts, pos);
            pos += len;
        }

        // return String.valueOf(m_allParts);
        return new String(m_allParts);
    }
}
