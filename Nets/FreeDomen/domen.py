# -*- coding: utf-8 -*-
"""
Created on Wed Dec  3 20:15:43 2020

@author: Kostya
"""
import codecs,socket
import requests
import pythonwhois
import threading
from datetime import datetime, date, time
f=codecs.open( "register.txt", "r", "utf_8_sig" )
reg=[]
for i in f:
    i=list(i.strip().split(";"))
    for j in i:
        if j.find("https")!=-1:
            domain=j.split("//")[-1].split("/")[0]
            reg.append(domain)

print(len(reg))
threads = []
for i in reg:
    details = pythonwhois.get_whois(i)
    if 'expiration_date' in details:
        print(i)
        if datetime.date(datetime.now())>datetime.date(details['expiration_date'][0]):
            print("You can buy it")
        else:
            print("You can't buy it(((")
            print(datetime.date(details['expiration_date'][0]))