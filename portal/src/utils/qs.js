import qs from "query-string";
import moment from "moment";

export function stringify(o) {
  if (o == null) {
    return "";
  }
  return qs.stringify(Object.keys(o).reduce((prev, key) => {
    let value = o[key];
    if (value != null && value !== '') {
      if (value instanceof Date) {
        value = moment(value).format('YYYY-MM-DD HH:mm:ss')
      }
      prev[key] = value;
    }
    return prev;
  }, {}));
}
