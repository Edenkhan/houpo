function getAge(birthday) {
  const today = new Date();
  let age = today.getFullYear() - birthday.getFullYear();
  const month = today.getMonth() - birthday.getMonth();
  return month < 0 || (month === 0 && today.getDate() < birthday.getDate()) ? age - 1 : age;
}

export function checkIdCardNumber(idCardNumber) {
  if (idCardNumber == null || !/^\d{17}[0-9X]$/.test(idCardNumber)) {
    return {valid: false};
  }

  const birthday = idCardNumber.substring(6, 14);
  const [_, year, month, day] = /^(\d{4})(\d{2})(\d{2})$/.exec(birthday);
  const birthdayDate = new Date(year, month - 1, day);
  if (birthdayDate.getFullYear() !== +year || birthdayDate.getMonth() !== (month - 1) || birthdayDate.getDate() !== +day) {
    return {valid: false};
  }

  const factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
  let seventeenSum = 0;
  for (let i = 0; i < 17; i++) {
    seventeenSum += idCardNumber.charAt(i) * factor[i];
  }

  if ("10X98765432"[seventeenSum % 11] !== idCardNumber.charAt(17)) {
    return {valid: false};
  }

  return {
    valid: true,
    birthday: birthdayDate,
    age: getAge(birthdayDate),
    gender: idCardNumber[16] % 2 === 0 ? '女' : '男'
  };
}

