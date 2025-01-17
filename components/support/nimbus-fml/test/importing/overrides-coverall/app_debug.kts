/* This Source Code Form is subject to the terms of the Mozilla Public
* License, v. 2.0. If a copy of the MPL was not distributed with this
* file, You can obtain one at http://mozilla.org/MPL/2.0/. */

import org.mozilla.experiments.nimbus.MockNimbus
import com.example.app.nimbus.*
import com.example.lib.nimbus.*

var injected: MockNimbus = MockNimbus(
    "property-overrides-test" to """{
        "variables-json": "variables-json"
    }"""
)

AppNimbus.initialize { injected }

val value = LibNimbus.features.overridesCoverall.value()
assert(value.noOverride == OverrideSource.NONE)
assert(value.scalar == OverrideSource.APP_FML)
assert(value.map[OverrideSource.APP_FML] == true)
assert(value.map[OverrideSource.CHANNEL_SPECIFIC] == true) // because we used release in the app fml.
assert(value.stringMap["app-fml"] == OverrideSource.APP_FML)
assert(value.nestedObject.scalar == OverrideSource.APP_FML)
assert(value.nestedObject.noOverride == OverrideSource.NONE)
