import urllib2 				# working with url
from bs4 import BeautifulSoup

def courseNumberTest(courseNumber): 				#function will iterate through range of possible course numbers testing to see if they exist
		try:
			url = urllib2.urlopen("http://students.yale.edu/oci/resultDetail.jsp?course=%s&term=201501" % courseNumber)
			soup = BeautifulSoup(url.read())			#generates Beautiful Soup object for site
			pageTextWithExtraComments = soup.get_text() 		#random commens seem to come along for the ride
			pageTextLength = len(pageTextWithExtraComments)
			endComments = pageTextWithExtraComments.rfind("-->",0,pageTextLength) + 3 		#point in page when stops being comments
			return pageTextWithExtraComments[endComments:pageTextLength]		#get part of page that is actually text
		except:
			return None