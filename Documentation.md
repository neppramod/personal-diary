#It covers documentation of the project.

# Introduction #

Personal Diary is a personal note taking application. It keeps our texts safe and later it we can search the text on any date. It lets browse our entry, edit them. User can browse recent entries from the home page, or go to list page to list out the entries. Currently it has very basic features like adding entries(no special formatting), searching, list with pagination(contextual based on date of entry). Still there might be inconsistencies with different pages and I did not try things like sitemesh or tiles to give a theme. It can be used as a starting spring, hibernate project on this topic. So Enjoy.


# Details #
# Solution #
Let me list out what I created

1. I created a simple web project with five pages
> a. index: My home page where I could go to see entries on specific date, search title with keyword, go to list page, see my latest entries and create a new entry. I want the browsing to be done with or without specific date. So the date traversing should be as easy as possibe and yet the page should have no nuisance(I want to create the text shown at recent list to be of specific size that would fit on the page). I want to go to view page by just clicking on the title. That is what I created on this home page.

> b. entry\_list: I want to have pagination. Browse list for some date, and page if pages exceed my given limit for page size. I want the entries to be in descending order(i.e my latest as recent). I want to go to view page just by clicking the title. I even want to create new entries from list page on the context basis(if I was browsing with some date, then it should be new entry for same dateelse it should be entry for today). Through some configuration file I want the limit on maximum no. of entries that the list page can hold for one page. I want to quickly go to home page from here. That is what you get on entry\_list.

> c. search: I want to search by any text that appears on the title, whether it is at front or back, small case or upper case. I want to have all the facilities like pagination, title view traversing, creating an entry(today date) and going back to home as in entry\_list from this search result page. So I created the search page.

> d. entry: I want to create entry by some date, or no date(today). I want to make sure the date is valid. The date is pre entered, whether it came with no date(today) or it should be an entry for some specific date. I don't want any restriction of date when to make an entry. I want to easily go back to where I came from(may be search, list, home) if I just remembered I don't want to make this entry any more. I want to save this entry. After saving my entry I want to go to see all the list of entries for the date which I just added the entry. So we can have as many entries we want to have even for a single day. It should only be limited by the storage we have on computers. So I created the entry page.

> e. edit\_entry: I want to act it like a view page and have all the facilities I had on the entry page too. So I want entries to be disabled unless I say I want to edit them. I would want to delete the entry or edit and update it. More over since this is my edit I don't want to tamper with the date I first created this entry so I don't want the date to be edited from here. This is because I want to keep track of when I first created this page.

Well this is what has drived me to create a personal diary, where I can record any text.

# Technical Details #

# Technology and tools used #
> a. Java
> b. Spring 3
> c. Jsp
> d. Jquery (Javascript)
> e. Calendar (Javascript)
> f. MySQL
> g. Hibernate
> h. Apace Tomcat

Java is an excellent language to create web projects. With J2EE we can create nice projects. Spring 3 is an addition on building projects as modular as we can. I used Spring to create a MVC project with the Spring @MVC part. Jsp is an excellent view technology to render html on web pages. I have not used Jquery to do any ajax stuffs. I have used only on one occassion to simply my Calendar code. Calendar is a javascript library that helps to draw monthly calender on the page very easily and we can browser to specific date and go to particular days list by clicking on any date. MySQL is a database software that supports SQL. For now I have used MyISAM. Since I have only one domain class changing to InnoDB is just adding one line on hibernate configuration and changing to the database to InnoDB. Hibernate is an excellent Object Relational Mapping framework that simplifies the work on database and reduces the amount of SQL needed to be written.

# Things that can be done to extend the feature of this project #

1. Add blob type to attach other media with the text and add wiki edit(may be).
2. We have used MySQL in this project. Write a script to backup mysql data to some other files
3. Add html equivalent for < as &lt; and > as &gt; because these entries are viewed on the web page
> (this is actually very simple).
4. Choose a better backup plan(no one can predict hard disk failure)
> i.e database or backup program so that we don't loose data, or may be other database type
5. If we want to access this page on public domain add security using spring 3 security framework
6. Some one may not like the very basic looks of the web pages so a better css would be welcomed one.
7. May be database needs to store unicode texts to support international characters.
8. We have searched only be title, may be add an option to find on text (description) too.

Possible places of errors:
> Since this is a personal project I have left some places where errors and inconsistencies could be generated.
1. Program saves on no title. That could disable the link to edit link where we could delete the file.
> Soln: We can create some button to directly delete the data.

2. A blue line appers on the calendar of home page. Well I have not figured out how it appeared.

3. May be I should have added some code to create better looks.