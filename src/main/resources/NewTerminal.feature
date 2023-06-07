#Feature: NewTerminalFeature
#
#  Scenario: newTerminalPopular
#    Given  اطلاعات_زیر_در_سیستم_نباشد
#
#
#
#
#
#
#
#
#      # sample BANK output
#      | row_number | psp_code  | acceptor_code   | trace_code   | local_date | local_time | recive_date | IBAN                       | deposite_date | deposite_type | deposite_circle_number | terminal_type | proccess_type | card_type | amount_shaparak | reference_code | deposite_flag | terminal_code       | orig_txn_info
#      | 000000001  | 581672022 | 015441980820001 | 000012       | 1401/12/01 | 13:14:07   | 1401/12/01  | IR660750031611212000012616 | 1401/12/01    | N             | 1                      | POS           | PU            | O         | 000000300000    | 200402139435   | D             | 98080991            |
#      | 000000002  | 581672031 | 000010000057247 | 000038       | 1401/12/01 | 21:43:39   | 1401/12/01  | IR650750023110276000020154 | 1401/12/01    | D             | 1                      | POS           | PU            | O         | 000000480000    | 234726000038   | D             | 05747060            | 00000000000000000000
#
#      # sample PSP output
#      | row_number | psp_code  | acceptor_code   | trace_code   | local_date | local_time | recive_date | IBAN                       | deposite_date | deposite_type | deposite_circle_number | terminal_type | proccess_type | card_type | amount_shaparak | reference_code | deposite_flag | acceptor_Commission | PSP_Commission       | PSP_NET_Commission | terminal_code
#      | 000000001  | 581672121 | 982022121100224 | 000000847172 | 1401/11/30 | 23:46:56   | 1401/11/30  | IR730190000000116916587004 | 1401/12/01    | D             | 1                      | POS           | PU            | O         | 000010000000    | 125872847172   | D             | C000000000000       | C000000000000        | C000000000000      | 98124376
#      | 000000002  | 581672121 | 663020481201180 | 000000847155 | 1401/11/30 | 23:46:50   | 1401/11/30  | IR100190000000306564529005 | 1401/12/01    | D             | 1                      | POS           | PU            | O         | 000000200000    | 125872847155   | D             | C000000000000       | C000000000000        | C000000000000      | 66057227
#
#
      # sample batch file input 27
#| 000000001  | 581672022 | 220014111000022 | 000000000003 | 1401/12/01 | 00:06:28   | 1401/12/01  | IR470600420302507312633001 | 1401/12/01    | N             | 1                      | POS           | PU            | O         | 000001000000    | 101284498238   | D             | C000000000000       | C000000000000        | C000000000000      | C000000000000 | 22048457 | 6037701545282771 |  |  |  |  |
  # new input file record
#|000000001|581672141|004354013831359|000000000000|1402/03/09|08:49:13|1402/03/09|IR480620010000102324943004        |1402/03/09|D|1|POS|PU|O|000000220000|022149122196|D|C000000000000|C000000000000|C000000000000|C000000000000|13095498|610433******4368   |                                             |C000000000000|C000000000000|5812|

#
#