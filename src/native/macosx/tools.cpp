#include <CoreServices/CoreServices.h>
#include "tools.h"
#include "common_tools.h"

MPCriticalRegionID critical_region;

bool getDictLong(CFDictionaryRef dict, CFStringRef key, long *key_value) {
	CFTypeRef val = CFDictionaryGetValue(dict, key);
	if (val != NULL) {
		CFTypeID type = CFGetTypeID(val);
		if (type == CFNumberGetTypeID())
			if (CFNumberGetValue((CFNumberRef)val, kCFNumberLongType, key_value))
				return true;
	}
	return false;
}
