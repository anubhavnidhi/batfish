package org.batfish.datamodel.matchers;

import javax.annotation.Nonnull;
import org.batfish.datamodel.OspfProcess;
import org.batfish.datamodel.Vrf;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

final class VrfMatchersImpl {

  static final class HasOspfProcess extends FeatureMatcher<Vrf, OspfProcess> {
    HasOspfProcess(@Nonnull Matcher<? super OspfProcess> subMatcher) {
      super(subMatcher, "A VRF with ospfProcess:", "ospfProcess");
    }

    @Override
    protected OspfProcess featureValueOf(Vrf actual) {
      return actual.getOspfProcess();
    }
  }
}