# Http Post Request Width Volley
HttpRequest class make simple post request using android volley library.

##Require

You must implement Response.Listener<String> **and** Response.ErrorListener to your activity. 

##Example

```javascript
	String  paramString = "test";
	double  paramDouble = 123.5d;
	int     paramInt = 111;

	new HttpRequest(MainActivity.this).
			addParam("paramString", paramString ).
			addParam("paramDouble", paramDouble).
			addParam("paramInt"   , paramInt ).
			setUrl("http://httpbin.org/post").
			sendRequest();
```


##HttpRequest Class
```javascript
	public class HttpRequest {

    private ArrayList<Param> parameters;
    private String url;
    private RequestQueue reqQue;
    private final Context listenerActivity;

    public HttpRequest(Context context) {
        parameters  = new ArrayList();
        reqQue      = Volley.newRequestQueue(context);
        listenerActivity = context;
    }

    public HttpRequest addParam(String key, Object value) {

        switch (value.getClass().getSimpleName()) {
            case "Integer":
                parameters.add(new Param(key, (int) value));
                break;
            case "Double":
                parameters.add(new Param(key, (Double) value));
                break;
            case "String":
                parameters.add(new Param(key, (String) value));
                break;
            default:
                return this;
        }
        return this;
    }

    public HttpRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public void sendRequest() {
        StringRequest request = new StringRequest(Request.Method.POST,
                url,
                (Response.Listener<String>) listenerActivity,
                (Response.ErrorListener) listenerActivity) {
                    @Override
                    protected Map<String, String> getParams() {
                        return HttpRequest.this.getParams();
                }
        };
        reqQue.add(request);
    }

    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        for (int i = 0; i < parameters.size(); i++)
            params.put(parameters.get(i).key, parameters.get(i).value);
        return params;
    }

    //region Parameter Class
    private class Param {
        public String key;
        public String value;

        Param(String key, String value) {
            this.key = key.trim();
            this.value = value;
        }

        Param(String key, int value) {
            this.key = key.trim();
            this.value = Integer.toString(value);
        }

        Param(String key, double value) {
            this.key = key.trim();
            this.value = Double.toString(value);
        }
    }
    //endregion
}

```
