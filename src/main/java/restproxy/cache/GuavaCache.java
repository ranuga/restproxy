package restproxy.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public class GuavaCache implements Cache {
    @SuppressWarnings("rawtypes")
    private final com.google.common.cache.Cache guavaCache;
    private final String                        name;

    public GuavaCache(final String name, final com.google.common.cache.Cache<?, ?> guavaCache) {
        this.name = name;
        this.guavaCache = guavaCache;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public com.google.common.cache.Cache<?, ?> getNativeCache() {
        return this.guavaCache;
    }

    @Override
    public ValueWrapper get(final Object key) {
        final Object value = this.guavaCache.getIfPresent(key);
        return (value != null ? new SimpleValueWrapper(value) : null);
    }

    @Override
    public <T> T get(Object o, Class<T> tClass) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void put(final Object key, final Object value) {
        this.guavaCache.put(key, value);
    }

    @Override
    public void evict(final Object key) {
        this.guavaCache.invalidate(key);
    }

    @Override
    public void clear() {
        this.guavaCache.invalidateAll();
    }
}