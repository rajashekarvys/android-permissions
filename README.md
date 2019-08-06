# android-permissions
Android run time permission
This is library is used to ask run time permssions easily.


# add dependance 

Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories 
            {
            .....
	maven { url 'https://jitpack.io' }
	}
	}
```
   
Add the dependency
```
dependencies {
	        implementation 'com.github.rajashekarvys:android-permissions:1.0.1'
	}
         
```

### Ask single permssion using 
```
PermissionsManager.askPermissions(
            context,
            Manifest.permission.READ_CONTACTS,
            this)
```
       
 ### For multiple permissions 
 ```
PermissionsManager.askPermissions(
            context,
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_SMS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), this)
```

### Callback from permissions 
Once user grant or deny permission you can recive call back as below
 ```
override fun onAccepted(list: ArrayList<String>) {
        for (s in list) {
            Log.d("Test===onAccepted ", s)
        }
    }

    override fun onDenied(list: ArrayList<String>) {
        for (s in list) {
            Log.d("Test===onDenied ", s)
        }
    }

    override fun onNeverAskPermissions(list: ArrayList<String>) {
        for (s in list) {
            Log.d("Test===onNeverAsk", s)
        }
    }
```

Note: Below 5.0 always onAccepted call back will be given for all permissions.
