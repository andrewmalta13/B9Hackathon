from PIL import Image
import urllib2, cStringIO

def getstats(im):
	f = cStringIO.StringIO(urllib2.urlopen(URL).read())
	pix = Image.open(f).load()
	xlocs=[90,150,210,270,330]
	yloc=248

	total=0
	num=0
	for i in range(1,6):
		bg=0
		barheight=-1
		while bg <= 10:
			barheight+=1
			bg=pix[xlocs[i-1], yloc-barheight][1]+pix[xlocs[i-1], yloc-barheight][2]
		print str(i)+', '+str(barheight)
		total+=barheight*i
		num+=barheight
	return '%.2f'%(total/float(num))






