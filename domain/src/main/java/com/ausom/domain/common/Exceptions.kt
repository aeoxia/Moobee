package com.ausom.domain.common

import java.io.IOException


class NoConnectivityException: IOException()
class LocationPermissionNotGrantedException: Exception()
class DateNotFoundException: Exception()