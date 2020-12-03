# -*- coding: utf-8 -*-
"""
Created on Wed Dec  3 19:31:54 2020

@author: Kostya
"""
import codecs,socket
f=codecs.open( "register.txt", "r", "utf_8_sig" )
reg=[]
for i in f:
    reg.append(list(i.strip().split(";")))
us_url=input("Input url: ")
domain=us_url.split("//")[-1].split("/")[0]
ip_domain=None
if socket.gethostbyname(domain):
    ip_domain=True
else:
    ip_domain=False
flag=1
for i in reg:
    if (us_url in i) or (domain in i) or (ip_domain in i) or (ip_domain==False):
        flag=0
        print("blocked")
        break
if flag==1:
    print("Good")