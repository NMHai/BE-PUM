0x0040127b:	call 0x00401561
0x00401561:	pushl %ebp
0x00401562:	movl %ebp, %esp
0x00401564:	subl %esp, $0x14<UINT8>
0x00401567:	movl %eax, 0x403000
0x0040156c:	andl -12(%ebp), $0x0<UINT8>
0x00401570:	andl -8(%ebp), $0x0<UINT8>
0x00401574:	pushl %esi
0x00401575:	pushl %edi
0x00401576:	movl %edi, $0xbb40e64e<UINT32>
0x0040157b:	movl %esi, $0xffff0000<UINT32>
0x00401580:	cmpl %eax, %edi
0x00401582:	je 0x00401591
0x00401591:	leal %eax, -12(%ebp)
0x00401594:	pushl %eax
0x00401595:	call GetSystemTimeAsFileTime@kernel32.dll
GetSystemTimeAsFileTime@kernel32.dll: API Node	
0x0040159b:	movl %eax, -8(%ebp)
0x0040159e:	xorl %eax, -12(%ebp)
0x004015a1:	movl -4(%ebp), %eax
0x004015a4:	call GetCurrentThreadId@kernel32.dll
GetCurrentThreadId@kernel32.dll: API Node	
0x004015aa:	xorl -4(%ebp), %eax
0x004015ad:	call 0x00000000
Unknown Node: Unknown Node	
