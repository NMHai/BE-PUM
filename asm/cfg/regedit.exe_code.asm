0x0101691e:	pushl $0x70<UINT8>
0x01016920:	pushl $0x10024d0<UINT32>
0x01016925:	call 0x01016b74
0x01016b74:	pushl $0x1016bc6<UINT32>
0x01016b79:	movl %eax, %fs:0
0x01016b7f:	pushl %eax
0x01016b80:	movl %eax, 0x10(%esp)
0x01016b84:	movl 0x10(%esp), %ebp
0x01016b88:	leal %ebp, 0x10(%esp)
0x01016b8c:	subl %esp, %eax
0x01016b8e:	pushl %ebx
0x01016b8f:	pushl %esi
0x01016b90:	pushl %edi
0x01016b91:	movl %eax, -8(%ebp)
0x01016b94:	movl -24(%ebp), %esp
0x01016b97:	pushl %eax
0x01016b98:	movl %eax, -4(%ebp)
0x01016b9b:	movl -4(%ebp), $0xffffffff<UINT32>
0x01016ba2:	movl -8(%ebp), %eax
0x01016ba5:	leal %eax, -16(%ebp)
0x01016ba8:	movl %fs:0, %eax
0x01016bae:	ret

0x0101692a:	xorl %ebx, %ebx
0x0101692c:	pushl %ebx
0x0101692d:	movl %edi, 0x10011e4
0x01016933:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32.dll: API Node	
0x01016935:	cmpw (%eax), $0x5a4d<UINT16>
0x0101693a:	jne 31
0x0101693c:	movl %ecx, 0x3c(%eax)
0x0101693f:	addl %ecx, %eax
0x01016941:	cmpl (%ecx), $0x4550<UINT32>
0x01016947:	jne 18
0x01016949:	movzwl %eax, 0x18(%ecx)
0x0101694d:	cmpl %eax, $0x10b<UINT32>
0x01016952:	je 0x01016973
0x01016973:	cmpl 0x74(%ecx), $0xe<UINT8>
0x01016977:	jbe -30
0x01016979:	xorl %eax, %eax
0x0101697b:	cmpl 0xe8(%ecx), %ebx
0x01016981:	setne %al
0x01016984:	movl -28(%ebp), %eax
0x01016987:	movl -4(%ebp), %ebx
0x0101698a:	pushl $0x2<UINT8>
0x0101698c:	call __set_app_type@msvcrt.dll
__set_app_type@msvcrt.dll: API Node	
0x01016992:	popl %ecx
0x01016993:	orl 0x1059d94, $0xffffffff<UINT8>
0x0101699a:	orl 0x1059d98, $0xffffffff<UINT8>
0x010169a1:	call __p__fmode@msvcrt.dll
__p__fmode@msvcrt.dll: API Node	
0x010169a7:	movl %ecx, 0x10195b8
0x010169ad:	movl (%eax), %ecx
0x01016bc6:	jmp _except_handler3@msvcrt.dll
_except_handler3@msvcrt.dll: API Node	
0x7c9032a8:	null
