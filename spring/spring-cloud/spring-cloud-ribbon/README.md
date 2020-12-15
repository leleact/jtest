# ribbon

## configuration
``` yaml
example-server:
  ribbon:
    ConnectTimeout: 2000
    ReadTimeout: 30000
```

`example-server` is remote application name like `http://example-server/http-method`
`ConnectTimeout` and `ReadTimeout` not take effect in `RestTemplate` see `RetryableRibbonLoadBalancingHttpClient`