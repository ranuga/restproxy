restproxy
=========

Application to simulate rest calls

Usage
To store a value that needs to be returned
http://<domain>:<port>/<contextroot>/store/{somename}

To restore
http://<domain>:<port>/<contextroot>/restore/{somename}



used Google Gauva caching to retain the value for 2 hours. System will clear the value if it is not used for over 2 hours
