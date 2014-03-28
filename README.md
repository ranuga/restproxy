restproxy
=========

Application to simulate rest calls

Usage

To store a value that needs to be returned (POST)

http://&lt;domain&gt;:&lt;port&gt;/&lt;contextroot&gt;/store/{somename}

To restore (GET)

http://&lt;domain&gt;:&lt;port&gt;/&lt;contextroot&gt;/restore/{somename}



used Google Gauva caching to retain the value for 2 hours. System will clear the value if it is not used for over 2 hours
