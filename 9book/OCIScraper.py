import urllib2 				# working with url
import json
from bs4 import BeautifulSoup

url = urllib2.urlopen('http://students.yale.edu/oci/resultDetail.jsp?course=24052&term=201501')

content = url.read()

soup = BeautifulSoup (content)
#print (soup.prettify())

print (soup.title.name)


print (soup.get_text())
