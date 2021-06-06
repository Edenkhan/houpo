import {stringify} from "../utils/qs";

export function navigateToPage(page, qs) {
  window.location.href = page + (qs != null ? ('?' + stringify(qs)) : '');
}
