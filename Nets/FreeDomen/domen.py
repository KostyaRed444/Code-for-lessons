# -*- coding: utf-8 -*-
"""
Created on Thu Dec  3 20:35:41 2020

@author: 1052126
"""

import codecs, socket
f=codecs.open("register.txt","r")
reg=[]
for i in f:
    reg.append(list(i.strip().split(";")))
    
for c, us_url in enumerate(reg):
    domain=us_url[2].split("//")[-1].split("/")[0]
    print(domain, end=" - ")
    ip_domain=None
    try:
        ip_domain=True if socket.gethostbyname(domain) else False
        flag=1
        for i in reg:
            if (us_url in i) or (domain in i) or (ip_domain in i) or (ip_domain==False):
                flag=0
                print("BLOCED/n")
                print("----------------------------")
                break
            if flag==1:
                print("GOOD........./n")
                print("----------------------------")
                break
    except Exception as e:
        print("GOOD/n")
        print("----------------------------")
        pass
    if c==500:
        break
        
