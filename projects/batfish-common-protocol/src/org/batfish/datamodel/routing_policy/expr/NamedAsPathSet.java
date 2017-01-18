package org.batfish.datamodel.routing_policy.expr;

import org.batfish.datamodel.AsPath;
import org.batfish.datamodel.AsPathAccessList;
import org.batfish.datamodel.BgpRoute;
import org.batfish.datamodel.routing_policy.Environment;

import com.fasterxml.jackson.annotation.JsonCreator;

public class NamedAsPathSet extends AsPathSetExpr {

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   private String _name;

   @JsonCreator
   private NamedAsPathSet() {
   }

   public NamedAsPathSet(String name) {
      _name = name;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      NamedAsPathSet other = (NamedAsPathSet) obj;
      if (_name == null) {
         if (other._name != null) {
            return false;
         }
      }
      else if (!_name.equals(other._name)) {
         return false;
      }
      return true;
   }

   public String getName() {
      return _name;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((_name == null) ? 0 : _name.hashCode());
      return result;
   }

   @Override
   public boolean matches(Environment environment) {
      AsPathAccessList list = environment.getConfiguration()
            .getAsPathAccessLists().get(_name);
      if (list != null) {
         BgpRoute bgpRoute = (BgpRoute) environment.getOriginalRoute();
         AsPath asPath = bgpRoute.getAsPath();
         boolean match = list.permits(asPath);
         return match;
      }
      else {
         environment.setError(true);
         return false;
      }
   }

   public void setName(String name) {
      _name = name;
   }

}
