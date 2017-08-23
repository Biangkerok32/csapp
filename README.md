# CSApp

Crawler and Scrapper for the PlayStore. Also find the keywords in the reviews by rating.
Generates a `.csv` with the info extracted and .txt for the reviews by rating
(4-5 starts, 3-4 starts, 2-3 starts, 0-2 starts) by one of the categories for apps (58 options the last time I tested)
and from the top_selling apps (free or paid).

In the data directory the files are generated. Also there is the script to use `RAKE` (`keywords.py`) that would take any `.txt` in the directory and find the keywords from it. A resume file is created (`resume.txt`) 

## Resources:

* [Apache Commons CSV](https://github.com/apache/commons-csv)
* [Jsoup](https://github.com/jhy/jsoup)
* [RAKE](https://github.com/zelandiya/RAKE-tutorial)

