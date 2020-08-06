import string
from random import choice
import random
import base64
with open('./src/main/resources/password.txt','w+') as file:
	for i in range(0,200):
		letters = string.ascii_lowercase
		ip=	str(random.randint(1, 255))+'.'+str(random.randint(1, 255))+'.'+str(random.randint(1, 255))+'.'+str(random.randint(1, 255))
		password=''.join(choice(letters) for i in range(12))
		base64.b64encode(bytes(password))
		file.write(ip+' '+password+'\n')

		